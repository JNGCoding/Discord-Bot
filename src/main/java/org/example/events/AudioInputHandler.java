package org.example.events;

import java.nio.ByteBuffer;

import net.dv8tion.jda.api.audio.AudioSendHandler;

public class AudioInputHandler implements AudioSendHandler {
    private final ByteBuffer buffer = ByteBuffer.allocate(9600);
    private final float frequency = 440.0f; // 440 Hz (A4 note)
    private final int sampleRate = 48000; // Discord's sample rate
    private int sampleIndex = 0;
    private final int volume = 100;

    public AudioInputHandler() {
    }

    @Override
    public boolean isOpus() {
        return false;
    }

    @Override
    public boolean canProvide() {
        return true;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        buffer.clear();
        for (int i = 0; i < 9600; i++) { // 20ms is 1/50th of a second
            byte sample = (byte) (volume * Math.sin(2.0 * Math.PI * frequency * sampleIndex / sampleRate));
            buffer.put(sample);
            sampleIndex++;
        }

        return buffer.flip();
    }
}
