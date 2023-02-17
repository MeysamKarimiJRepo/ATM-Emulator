package com.meysam.emulator.atm.dto;

public class AuthenticationResponse {
    private String pan;
    private boolean authenticated;

    public AuthenticationResponse(String pan, boolean authenticated) {
        this.pan = pan;
        this.authenticated = authenticated;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
