package br.com.sebratel.estoque.exceptions;

public class InvalidMassiveRequestException extends RuntimeException {
    public InvalidMassiveRequestException(String message) {
        super(message);
    }
}
