/**
 * @(#)GlobalRestExceptionHandler.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.handler;

import br.com.camisadez.rest.api.constants.ErrorType;
import br.com.camisadez.rest.api.dto.exception.ErrorResponse;
import br.com.camisadez.rest.api.exception.ActionNotPermittedException;
import br.com.camisadez.rest.api.exception.IncorrectlyFormattedDateException;
import br.com.camisadez.rest.api.utils.ExceptionUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe <code>GlobalRestExceptionHandler</code>.
 *
 * Essa classe é responsável por tratar todos os erros da aplicação retornar um objeto resposta.
 * */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * Tratamento de exceção <code>{@link EntityNotFoundException}</code>.
     * Essa exceção é lançada apenas quando em alguma parte do sistema não é encontrado uma entidade no banco de dados.
     *
     * @param e
     * @return
     * */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getErrorResponse(e.getMessage(), ErrorType.BAD_REQUEST));
    }

    /**
     * Tratamento de exeção <code>{@link MethodArgumentNotValidException}</code>.
     * Essa exeção é lançada em validações inválidas.
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getErrorResponse(e.getMessage(), ErrorType.BAD_REQUEST));
    }

    /**
     * Tratamento de exeção <code>{@link IncorrectlyFormattedDateException}</code>.
     * Essa exeção é lançada em datas com a formatação incorreta.
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IncorrectlyFormattedDateException.class})
    public ResponseEntity<ErrorResponse> incorrectlyFormattedDateException(IncorrectlyFormattedDateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getErrorResponse(e.getMessage(), ErrorType.BAD_REQUEST));
    }

    /**
     * Tratamento de exeção <code>{@link ActionNotPermittedException}</code>.
     * Essa exeção é lançada quando uma ção não é permitida no sistema.
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ActionNotPermittedException.class})
    public ResponseEntity<ErrorResponse> actionNotPermittedException(ActionNotPermittedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getErrorResponse(e.getMessage(), ErrorType.BAD_REQUEST));
    }
}
