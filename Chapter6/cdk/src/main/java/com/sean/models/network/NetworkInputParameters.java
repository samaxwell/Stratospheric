package com.sean.models.network;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

public class NetworkInputParameters {

    @Getter
    private final Optional<String> sslCertificateArn;

    public NetworkInputParameters(String sslCertificateArn) {
        Objects.requireNonNull(sslCertificateArn);
        this.sslCertificateArn = Optional.of(sslCertificateArn);
    }

    public NetworkInputParameters() {
        this.sslCertificateArn = Optional.empty();
    }
}
