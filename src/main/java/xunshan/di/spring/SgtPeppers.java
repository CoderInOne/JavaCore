package xunshan.di.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public PlayParams playParams;

    public SgtPeppers() {

    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

    @Autowired
    public void setPlayParams(PlayParams playParams) {
        this.playParams = playParams;
    }
}
