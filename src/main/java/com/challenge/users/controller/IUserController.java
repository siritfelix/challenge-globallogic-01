package com.challenge.users.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.ErrorResponseDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.service.impl.UserDetailsImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ms-users", description = "Microservicio de consulta de usuarios")
@RequestMapping(IUserController.URI)
public interface IUserController {
	public static final String URI = "api/v1";
	public static final String SING_UP = "/sing-up";
	public static final String LOGIN = "/login";

	@Operation(summary = "Alta de usuario", description = "Alta de usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserSingUpRequestDto.class))),
			@ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping(SING_UP)
	public ResponseEntity<UserResponseDto> singUpUser(UserSingUpRequestDto userSingUpRequestDto);

	@Operation(summary = "Autenticacion de usuario", description = "Autenticacion de usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserLoginResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping(LOGIN)
	public ResponseEntity<UserLoginResponseDto> loginUser(String token, UserDetailsImpl userDetailsImpl);
}
