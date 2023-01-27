package com.example.kaninosCa.bucket;

public enum BucketName {

    PROFILE_IMAGE("kaninosbucket");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }

}
