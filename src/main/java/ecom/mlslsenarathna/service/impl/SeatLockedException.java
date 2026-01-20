package ecom.mlslsenarathna.service.impl;

public class SeatLockedException extends Throwable {
    private final long secondsRemaining;

    public SeatLockedException(long secondsRemaining) {
        super("Seat is currently held by another user.");
        this.secondsRemaining = secondsRemaining;
    }
    public long getSecondsRemaining() {
        return secondsRemaining;
    }

}
