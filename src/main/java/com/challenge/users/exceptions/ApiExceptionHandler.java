package com.challenge.users.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.challenge.users.config.ErrorMessagerMap;
import com.challenge.users.dto.response.ErrorDto;
import com.challenge.users.dto.response.ErrorResponseDto;

@ControllerAdvice
public class ApiExceptionHandler {
	private final ErrorMessagerMap errorMessagerMap;

	public ApiExceptionHandler(ErrorMessagerMap errorMessagerMap) {
		this.errorMessagerMap = errorMessagerMap;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFound.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> notFoundRequest(HttpServletRequest request, NotFound exception) {
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(new ArrayList<>(Arrays.asList(ErrorDto.builder()
						.detail(errorMessagerMap.getMessager()
								.get(404))
						.codigo(404).timestamp(LocalDateTime.now())
						.build())))
				.build(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequest.class,
			HttpMessageNotReadableException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> badRequest(HttpServletRequest request, Exception exception) {
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(new ArrayList<>(Arrays.asList(ErrorDto.builder()
						.detail(errorMessagerMap.getMessager()
								.get(400))
						.codigo(400).timestamp(LocalDateTime.now())
						.build())))
				.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> onConstraintValidationException(
			ConstraintViolationException e) {
		List<ErrorDto> messages = new ArrayList<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			messages.add(ErrorDto.builder()
					.detail(violation.getPropertyPath().toString().concat(": ").concat(violation
							.getMessage()))
					.codigo(400).timestamp(LocalDateTime.now())
					.build());
		}
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(messages).build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> onMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		List<ErrorDto> messages = new ArrayList<>();

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			messages.add(ErrorDto.builder().detail(errorMessagerMap.getMessager().get(4001).concat(
					fieldError.getField().concat("-> ").concat(fieldError
							.getDefaultMessage())))
					.codigo(400).timestamp(LocalDateTime.now())
					.build());
		}
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(messages).build(), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ ConflictException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> conflictException(HttpServletRequest request, ConflictException exception) {
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(new ArrayList<>(Arrays.asList(ErrorDto.builder()
						.detail(errorMessagerMap.getMessager()
								.get(exception.getCode()))
						.codigo(exception
								.getCode())
						.timestamp(LocalDateTime.now())
						.build())))
				.build(), HttpStatus.CONFLICT);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class,
			InternalError.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> fatalErrorUnexpectedException(HttpServletRequest request,
			Exception exception) {
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(new ArrayList<>(Arrays.asList(ErrorDto.builder()
						.detail(errorMessagerMap.getMessager()
								.get(500))
						.codigo(500).timestamp(LocalDateTime.now())
						.build())))
				.build(), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ Unauthorized.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDto> unauthorizedException(HttpServletRequest request,
			Unauthorized exception) {
		return new ResponseEntity<ErrorResponseDto>(ErrorResponseDto.builder()
				.error(new ArrayList<>(Arrays.asList(ErrorDto.builder()
						.detail(errorMessagerMap.getMessager()
								.get(401))
						.codigo(401).timestamp(LocalDateTime.now())
						.build())))
				.build(), HttpStatus.BAD_REQUEST);
	}

}
