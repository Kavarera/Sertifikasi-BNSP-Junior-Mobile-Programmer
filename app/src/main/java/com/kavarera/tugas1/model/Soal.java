package com.kavarera.tugas1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Soal implements Parcelable {
    private String jawaban;
    private int gambarUrl;
    public Soal(String jawaban, int gambarUrl) {
        this.jawaban = jawaban;
        this.gambarUrl = gambarUrl;
    }

    protected Soal(Parcel in) {
        jawaban = in.readString();
        gambarUrl = in.readInt();
    }

    public static final Creator<Soal> CREATOR = new Creator<Soal>() {
        @Override
        public Soal createFromParcel(Parcel in) {
            return new Soal(in);
        }

        @Override
        public Soal[] newArray(int size) {
            return new Soal[size];
        }
    };

    public String getJawaban() {
        return jawaban;
    }

    public int getGambarUrl() {
        return gambarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(jawaban);
        parcel.writeInt(gambarUrl);
    }
}
