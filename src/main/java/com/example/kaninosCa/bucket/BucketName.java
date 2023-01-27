package com.example.kaninosCa.bucket;

public enum BucketName {

    BUCKET_NAME("kaninosbucket");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }

}
