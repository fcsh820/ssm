package com.demo.ssm.util.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * openSSL
 * Created by Administrator on 2015/12/7.
 */
public class RSA {
    private static final String DEFAULT_PUBLIC_KEY=
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChDzcjw/rWgFwnxunbKp7/4e8w" + "\r" +
            "/UmXx2jk6qEEn69t6N2R1i/LmcyDT1xr/T2AHGOiXNQ5V8W4iCaaeNawi7aJaRht" + "\r" +
            "Vx1uOH/2U378fscEESEG8XDqll0GCfB1/TjKI2aitVSzXOtRs8kYgGU78f7VmDNg" + "\r" +
            "XIlk3gdhnzh+uoEQywIDAQAB" + "\r";

    private static final String DEFAULT_PRIVATE_KEY=
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKEPNyPD+taAXCfG" + "\r" +
            "6dsqnv/h7zD9SZfHaOTqoQSfr23o3ZHWL8uZzINPXGv9PYAcY6Jc1DlXxbiIJpp4" + "\r" +
            "1rCLtolpGG1XHW44f/ZTfvx+xwQRIQbxcOqWXQYJ8HX9OMojZqK1VLNc61GzyRiA" + "\r" +
            "ZTvx/tWYM2BciWTeB2GfOH66gRDLAgMBAAECgYBp4qTvoJKynuT3SbDJY/XwaEtm" + "\r" +
            "u768SF9P0GlXrtwYuDWjAVue0VhBI9WxMWZTaVafkcP8hxX4QZqPh84td0zjcq3j" + "\r" +
            "DLOegAFJkIorGzq5FyK7ydBoU1TLjFV459c8dTZMTu+LgsOTD11/V/Jr4NJxIudo" + "\r" +
            "MBQ3c4cHmOoYv4uzkQJBANR+7Fc3e6oZgqTOesqPSPqljbsdF9E4x4eDFuOecCkJ" + "\r" +
            "DvVLOOoAzvtHfAiUp+H3fk4hXRpALiNBEHiIdhIuX2UCQQDCCHiPHFd4gC58yyCM" + "\r" +
            "6Leqkmoa+6YpfRb3oxykLBXcWx7DtbX+ayKy5OQmnkEG+MW8XB8wAdiUl0/tb6cQ" + "\r" +
            "FaRvAkBhvP94Hk0DMDinFVHlWYJ3xy4pongSA8vCyMj+aSGtvjzjFnZXK4gIjBjA" + "\r" +
            "2Z9ekDfIOBBawqp2DLdGuX2VXz8BAkByMuIh+KBSv76cnEDwLhfLQJlKgEnvqTvX" + "\r" +
            "TB0TUw8avlaBAXW34/5sI+NUB1hmbgyTK/T/IFcEPXpBWLGO+e3pAkAGWLpnH0Zh" + "\r" +
            "Fae7oAqkMAd3xCNY6ec180tAe57hZ6kS+SYLKwb4gGzYaCxc22vMtYksXHtUeamo" + "\r" +
            "1NMLzI2ZfUoX" + "\r";

    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * 获取私钥
     * @return 当前的私钥对象
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair(){
        KeyPairGenerator keyPairGen= null;
        try {
            keyPairGen= KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair= keyPairGen.generateKeyPair();
        this.privateKey= (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey= (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * 从文件中输入流中加载公钥
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }


    /**
     * 从字符串中加载公钥
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
            this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * @throws Exception
     */
    public void loadPrivateKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public void loadPrivateKey(String privateKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            this.privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     * @param publicKey 公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{
        if(publicKey== null){
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output= cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception{
        if (privateKey== null){
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output= cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 字节数据转十六进制字符串
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0; i<data.length; i++){
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);
            //取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i<data.length-1){
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args){
        RSA rsa= new RSA();
        //rsaEncrypt.genKeyPair();

        //加载公钥
        try {
            rsa.loadPublicKey(RSA.DEFAULT_PUBLIC_KEY);
            System.out.println("加载公钥成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("加载公钥失败");
        }

        //加载私钥
        try {
            rsa.loadPrivateKey(RSA.DEFAULT_PRIVATE_KEY);
            System.out.println("加载私钥成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("加载私钥失败");
        }

        //测试字符串
        String encryptStr= "Test String chaijunkun";

        try {
            //加密
            byte[] cipher = rsa.encrypt(rsa.getPublicKey(), encryptStr.getBytes());
            //解密
            byte[] plainText = rsa.decrypt(rsa.getPrivateKey(), cipher);
            System.out.println("密文长度:"+ cipher.length);
            System.out.println(RSA.byteArrayToString(cipher));
            System.out.println("明文长度:"+ plainText.length);
            System.out.println(RSA.byteArrayToString(plainText));
            System.out.println(new String(plainText));

            System.err.println(RSA.DEFAULT_PUBLIC_KEY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
