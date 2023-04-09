package com.gomeals.service.implementation;

import com.gomeals.model.Complain;
import com.gomeals.repository.ComplainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComplainServiceImplementationTest {
        @Mock
        private ComplainRepository complainRepository;

        @InjectMocks
        private ComplainServiceImplementation complainServiceImplementation;

        @Test
        void getAllComplains() {
                // when the service calls get all complains then it should return a list of
                // complains
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);

                when(complainRepository.findAll()).thenReturn(Arrays.asList(
                                new Complain(1, date, "pizza had no cheese", "refunded meal",
                                                "initiated", 1, 2, 2),
                                new Complain(2, date, "missing 1 taco", "refunded meal",
                                                "initiated", 1, 2, 2)));
                List<Complain> result = complainServiceImplementation.getAllComplains();

                assertEquals(2, result.get(0).getDeliveryId());
                assertEquals(2, result.get(1).getComplainId());
        }

        @Test
        void getComplainById() {
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);

                when(complainRepository.findById(1)).thenReturn(
                                Optional.of(new Complain(1, date, "pizza had no cheese", "refunded meal",
                                                "initiated", 1, 2, 2)));
                Complain complain = complainServiceImplementation.getComplainById(1);

                assertEquals(2, complain.getSupplierId());
        }

        @Test
        void deleteComplain() {
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                Complain complain = new Complain(1, date, "pizza had no cheese", "refunded meal",
                                "initiated", 1, 2, 2);

                when(complainRepository.findById(1)).thenReturn(Optional.of(complain));
                complainServiceImplementation.deleteComplain(complain.getComplainId());
                verify(complainRepository).deleteById(complain.getComplainId());
        }

        @Test
        void updateComplain() {
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);

                Complain complain = new Complain(1, date, "pizza had no cheese", "refunded meal",
                                "initiated", 1, 2, 2);
                Complain newComplain = new Complain(1, date, "pizza had no cheese", "refunded meal",
                                "closed", 1, 2, 2);

                when(complainRepository.findById(1)).thenReturn(Optional.of(complain));
                when(complainRepository.save(complain)).thenReturn(newComplain);

                Complain updatedComplain = complainServiceImplementation.updateComplain(complain);

                assertEquals("closed", updatedComplain.getStatus());

        }

}
