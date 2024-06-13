package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("member 저장하기")
    void saveMember() throws Exception {
        //given
        MemberRequest request = new MemberRequest("새로운 멤버", 21); // 요청 생성
        MemberResponse response = new MemberResponse(1L, "새로운 멤버", 21);
        Mockito.when(memberService.save(any(MemberRequest.class)))
                .thenReturn(response); // memberService.save를 호출하면 response 반환하기

        //when-then
        this.mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(
                        "{class-name}/{method-name}", // 저장될 위치
                        requestFields( //요청 필드들
                                fieldWithPath("name").type(JsonFieldType.STRING).description("새로운 멤버의 이름입니다."),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("새로운 멤버의 나이입니다.")
                        ),
                        responseFields( // 응답 필드들
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("새로운 멤버의 아이디입니다."),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("새로운 멤버의 이름입니다."),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("새로운 멤버의 나이입니다.")
                        )
                ));
    }

    @Test
    @DisplayName("member 가져오기")
    void getMember() throws Exception {
        //given
        MemberResponses responses = new MemberResponses(
                List.of(
                        new MemberResponse(1L, "새로운 멤버1", 21),
                        new MemberResponse(2L, "새로운 멤버2", 21)
                )
        );

        Mockito.when(memberService.findAll())
                .thenReturn(responses); // memberService.findAll을 호출하면 responses 반환하기

        //when-then
        this.mockMvc.perform(get("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(
                        "{class-name}/{method-name}", // 저장될 위치
                        responseFields( // 응답 필드들
                                fieldWithPath("memberResponses").type(JsonFieldType.ARRAY).description("전체 멤버 목록"),
                                fieldWithPath("memberResponses[].id").type(JsonFieldType.NUMBER).description("새로운 멤버의 아이디입니다."),
                                fieldWithPath("memberResponses[].name").type(JsonFieldType.STRING).description("새로운 멤버의 이름입니다."),
                                fieldWithPath("memberResponses[].age").type(JsonFieldType.NUMBER).description("새로운 멤버의 나이입니다.")
                        )
                ));
    }

    @Test
    @DisplayName("일치하는 이름 가져오기")
    void findByName() throws Exception {
        //given
        MemberResponse response = new MemberResponse(1L, "새로운 멤버", 21);
        Mockito.when(memberService.findByName(any()))
                .thenReturn(response); // memberService.findByName을 호출하면 response 반환하기

        //when-then
        this.mockMvc.perform(get("/member-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "새로운 멤버")
                ).andExpect(status().isOk())

                .andDo(MockMvcRestDocumentation.document(
                        "{class-name}/{method-name}", // 저장될 위치
                        queryParameters(
                                parameterWithName("name").description("필터링할 멤버 이름입니다.")
                        ),
                        responseFields( // 응답 필드들
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("새로운 멤버의 아이디입니다."),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("새로운 멤버의 이름입니다."),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("새로운 멤버의 나이입니다.")
                        )
                ));
    }

    @Test
    @DisplayName("아이디를 기반으로 멤버 가져오기")
    void findById() throws Exception {
        //given
        MemberResponse response = new MemberResponse(1L, "새로운 멤버", 21);
        Mockito.when(memberService.findById(anyLong()))
                .thenReturn(response); // memberService.findById을 호출하면 response 반환하기

        //when-then
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/member/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())

                .andDo(MockMvcRestDocumentation.document(
                        "{class-name}/{method-name}",
                        pathParameters(// path parameter 설명
                                parameterWithName("id").description("찾아올 멤버 아이디입니다.")
                        ),
                        responseFields( // 응답 필드들
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("새로운 멤버의 아이디입니다."),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("새로운 멤버의 이름입니다."),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("새로운 멤버의 나이입니다.")
                        )
                ));
    }

}
