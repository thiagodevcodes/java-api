package br.com.thiago.orcamento.service.exceptions;

public class ConstraintException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ConstraintException(String msg) {
        super(msg);
    }
    public ConstraintException(String msg, Throwable cause) {
        super(msg, cause);
    }
}