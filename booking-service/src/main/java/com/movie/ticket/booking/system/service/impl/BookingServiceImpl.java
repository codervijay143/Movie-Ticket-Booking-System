package com.movie.ticket.booking.system.service.impl;

import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.commons.dto.BookingStatus;
import com.movie.ticket.booking.system.service.BookingService;
import com.movie.ticket.booking.system.service.broker.PaymentServiceBroker;
import com.movie.ticket.booking.system.service.entity.BookingEntity;
import com.movie.ticket.booking.system.service.kafka.publisher.BookingServiceKafkaPublisher;
import com.movie.ticket.booking.system.service.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingServiceKafkaPublisher bookingServiceKafkaPublisher;
    private final PaymentServiceBroker paymentServiceBroker;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUserId(bookingDTO.getUserId());
        bookingEntity.setMovieId(bookingDTO.getMovieId());
        bookingEntity.setTransactionId(bookingDTO.getTransactionId());
        bookingEntity.setSeatsBooked(bookingDTO.getSeatsBooked());
        bookingEntity.setShowDate(LocalDate.now().plusDays(1L));
        bookingEntity.setShowTime(LocalTime.now().plusHours(24L));
        bookingEntity.setCreatedDateTime(LocalDateTime.now());
        bookingEntity.setBookingStatus(BookingStatus.PENDING);
        bookingEntity.setBookingAmount(bookingDTO.getBookingAmount());
        bookingEntity.setMovieName(bookingDTO.getMovieName());
        this.bookingRepository.save(bookingEntity); // create a booking with booking status as PENDING

        BookingDTO bookingDTO1 = BookingDTO.builder()
                .showTime(bookingEntity.getShowTime())
                .showDate(bookingEntity.getShowDate())
                .bookingId(bookingEntity.getBookingId())
                .bookingStatus(BookingStatus.PENDING)
                .userId(bookingEntity.getUserId())
                .movieId(bookingEntity.getMovieId())
                .bookingAmount(bookingEntity.getBookingAmount())
                .transactionId(bookingEntity.getTransactionId())
                .seatsBooked(bookingEntity.getSeatsBooked())
                .createdDateTime(LocalDateTime.now())
                .movieName(bookingEntity.getMovieName())
                .build();
        this.bookingServiceKafkaPublisher.publishToPaymentRequestTopic(bookingDTO1);
        return bookingDTO1;
    }

    @Override
    @Transactional
    public void processBooking(BookingDTO bookingDTO) {
        Optional<BookingEntity> bookingEntityOptional = this.bookingRepository.findById(bookingDTO.getBookingId());
        if (bookingEntityOptional.isPresent()) {
            BookingEntity bookingEntity = bookingEntityOptional.get();
            bookingEntity.setBookingStatus(bookingDTO.getBookingStatus());
        }
    }

    @Override
    public List<BookingDTO> getBookings() {
        List<BookingEntity> bookingEntityList = (List<BookingEntity>) this.bookingRepository.findAll();
        List<BookingDTO> bookingDTOList=new ArrayList<>();
        for(BookingEntity bookingEntity:bookingEntityList){
            BookingDTO bookingDto = BookingDTO.builder()
                    .showTime(bookingEntity.getShowTime())
                    .showDate(bookingEntity.getShowDate())
                    .bookingId(bookingEntity.getBookingId())
                    .bookingStatus(bookingEntity.getBookingStatus())
                    .userId(bookingEntity.getUserId())
                    .movieId(bookingEntity.getMovieId())
                    .bookingAmount(bookingEntity.getBookingAmount())
                    .transactionId(bookingEntity.getTransactionId())
                    .seatsBooked(bookingEntity.getSeatsBooked())
                    .createdDateTime(LocalDateTime.now())
                    .movieName(bookingEntity.getMovieName())
                    .build();
            bookingDTOList.add(bookingDto);
        }
        return bookingDTOList;
    }
}

