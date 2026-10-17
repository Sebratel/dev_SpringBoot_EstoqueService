package br.com.sebratel.estoque.exceptions;

public class FeatureNotImplementedException extends RuntimeException {
    public FeatureNotImplementedException(String message) {
        super(message);
    }
}