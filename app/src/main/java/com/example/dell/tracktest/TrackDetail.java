package com.example.dell.tracktest;

/**
 * Created by dell on 2017/6/4.
 */

public class TrackDetail {
    private int id;
    private double lat;
    private double lng;
    private Track track;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public Track getTrack() {
        return track;
    }
    public void setTrack(Track track) {
        this.track = track;
    }
    public TrackDetail(int id, double lat, double lng) {
        super();
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }
    public TrackDetail(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }
    public TrackDetail() {
        super();
    }

}
