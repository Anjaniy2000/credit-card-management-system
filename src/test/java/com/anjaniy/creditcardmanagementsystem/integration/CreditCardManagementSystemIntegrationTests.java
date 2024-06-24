package com.anjaniy.creditcardmanagementsystem.integration;

import com.anjaniy.creditcardmanagementsystem.models.dto.AddressDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.CreditCardDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.UserDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.request.CreateUserRequest;
import com.anjaniy.creditcardmanagementsystem.models.dto.request.GetUsersRequest;
import com.anjaniy.creditcardmanagementsystem.models.enums.Gender;
import com.anjaniy.creditcardmanagementsystem.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CreditCardManagementSystemIntegrationTests extends CreditCardManagementSystemBaseIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private static final String NAME = "Jay Shah";
    private static final String EMAIL = "jay101shah@gmail.com";
    private static final String PHONE_NUMBER = "8799535110";
    private static final Gender GENDER = Gender.MALE;
    private static AddressDto address;
    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String POSTAL = "postal";
    private static final Integer ZIP = 390023;
    private static List<CreditCardDto> creditCards;
    private static final Long CC_NUMBER = 111222L;
    private static final String EXPIRY_DATE = "2021-08-01";
    private static final Double CREDIT_LIMIT = 2100D;

    @BeforeAll
    static void setupBeforeAll(){
        container.start();
    }

    @BeforeEach
    void setupBeforeEach(){
        userRepository.deleteAll();
        address = new AddressDto(STREET, CITY, STATE, POSTAL, ZIP);
        creditCards = List.of(
                new CreditCardDto(CC_NUMBER, EXPIRY_DATE, CREDIT_LIMIT),
                new CreditCardDto(333444L, "2020-09-03", 1500D)
        );
    }

    @AfterAll
    static void setupAfterAll(){
        container.stop();
    }



    @Test
    void test_check(){
        try {
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/check"));
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test_createUser(){
        CreateUserRequest request = new CreateUserRequest(NAME, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
        try {
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(request.getName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(request.getEmail())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", CoreMatchers.is(request.getAddress().getStreet())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", CoreMatchers.is(request.getAddress().getZip())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCards.size()", CoreMatchers.is(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test_getUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest(NAME, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
        try {
            ResultActions createUserResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createUserRequest)));

            String id = JsonPath.read(createUserResponse.andReturn().getResponse().getContentAsString(), "$.id");

            ResultActions getUserResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + id));
            getUserResponse.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(createUserRequest.getName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(createUserRequest.getEmail())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", CoreMatchers.is(createUserRequest.getAddress().getStreet())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", CoreMatchers.is(createUserRequest.getAddress().getZip())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCards.size()", CoreMatchers.is(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void test_getUsers(){
        int toAdd = 10;
        for(int i = 0 ; i < toAdd; i++){
            CreateUserRequest createUserRequest = new CreateUserRequest(NAME + "_" + i, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
            try {
                ResultActions createUserResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        GetUsersRequest getUsersRequest = new GetUsersRequest(0, 10);
        try {
            ResultActions getUsersResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(getUsersRequest)));

            getUsersResponse.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", CoreMatchers.is(10)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void test_updateUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest(NAME, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
        try {
            ResultActions createUserResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createUserRequest)));

            String id = JsonPath.read(createUserResponse.andReturn().getResponse().getContentAsString(), "$.id");

            UserDto updateUserRequest = new UserDto(id, "Jay Shah S.", createUserRequest.getEmail(), createUserRequest.getPhoneNumber(), Gender.FEMALE, createUserRequest.getAddress(), createUserRequest.getCreditCards());

            ResultActions updateUserResponse = mockMvc.perform(MockMvcRequestBuilders.put("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateUserRequest)));
            updateUserResponse.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Jay Shah S.")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(createUserRequest.getEmail())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.gender", CoreMatchers.is(Gender.FEMALE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", CoreMatchers.is(createUserRequest.getAddress().getStreet())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", CoreMatchers.is(createUserRequest.getAddress().getZip())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCards.size()", CoreMatchers.is(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void test_updateUserWithAddressAndCreditCards(){
        CreateUserRequest createUserRequest = new CreateUserRequest(NAME, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
        try {
            ResultActions createUserResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createUserRequest)));

            String id = JsonPath.read(createUserResponse.andReturn().getResponse().getContentAsString(), "$.id");

            //updated the address:
            AddressDto address = createUserRequest.getAddress();
            address.setCity("updated city");
            address.setZip(390025);

            //updated the credit cards:
            List<CreditCardDto> creditCards = new ArrayList<>(createUserRequest.getCreditCards());
            creditCards.get(0).setCreditLimit(3400D);
            creditCards.add(new CreditCardDto(999999L, "2024-12-24", 3000D));

            //update request:
            UserDto updateUserRequest = new UserDto(id, "Jay Shah S.", createUserRequest.getEmail(), createUserRequest.getPhoneNumber(), Gender.FEMALE, address, creditCards);

            ResultActions updateUserResponse = mockMvc.perform(MockMvcRequestBuilders.put("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateUserRequest)));
            updateUserResponse.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Jay Shah S.")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(createUserRequest.getEmail())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.gender", CoreMatchers.is(Gender.FEMALE.name())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.city", CoreMatchers.is(address.getCity())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", CoreMatchers.is(address.getZip())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCards.size()", CoreMatchers.is(3)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test_deleteUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest(NAME, EMAIL, PHONE_NUMBER, GENDER, address, creditCards);
        try {
            ResultActions createUserResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createUserRequest)));

            String id = JsonPath.read(createUserResponse.andReturn().getResponse().getContentAsString(), "$.id");

            ResultActions getUserResponse = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/" + id));
            getUserResponse.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(createUserRequest.getName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(createUserRequest.getEmail())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", CoreMatchers.is(createUserRequest.getAddress().getStreet())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", CoreMatchers.is(createUserRequest.getAddress().getZip())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCards.size()", CoreMatchers.is(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
