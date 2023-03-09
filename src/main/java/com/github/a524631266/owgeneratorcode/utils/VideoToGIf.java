package com.github.a524631266.owgeneratorcode.utils;


import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;
import ws.schild.jave.info.VideoSize;


import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author : Liangliang.Zhang4
 * @version : 1.0
 * @date : 2023/3/5
 */
public class VideoToGIf {

    //输出格式
    private static final String outputFormat = "gif";


    /**
     * 获得转化后的文件名
     *
     * @param sourceFilePath : 源视频文件路径
     * @return
     */
    public static String getNewFileName(String sourceFilePath) {
        File source = new File(sourceFilePath);
        String fileName = source.getName().substring(0, source.getName().lastIndexOf("."));
        return fileName + "." + outputFormat;
    }

    /**
     * 转化音频格式
     * @return
     */
    public static void transform(VideoParam videoParam) {
        File source = new File(videoParam.getSourceFolderPath());
        File target = new File(videoParam.getTargetFolderPath());
        try {
            //获得原视频的分辨率
//            Constructor<MultimediaObject> constructor = MultimediaObject.class.getConstructor(File.class);
//            MultimediaObject mediaObject = constructor.newInstance(source);
            // bug class no defined 异常，因为不是同一个线程，可能会出现不同类加载器之间去取类，导致找不到的问题。
            MultimediaObject mediaObject = new MultimediaObject(source);
            MultimediaInfo multimediaInfo = mediaObject.getInfo();
            VideoInfo videoInfo = multimediaInfo.getVideo();
            VideoSize sourceSize = videoInfo.getSize();
            //设置视频属性
            VideoAttributes video = new VideoAttributes();
            video.setCodec(outputFormat);
            //设置视频帧率 正常为10 ，值越大越流畅
            video.setFrameRate(videoParam.getFrameRate());
            //设置视频分辨率
            VideoSize targetSize = new VideoSize(new BigDecimal(sourceSize.getWidth() * videoParam.getScale()).intValue() ,
                    new BigDecimal(sourceSize.getHeight() * videoParam.getScale()).intValue());
            video.setSize(targetSize);
            //设置转码属性
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setVideoAttributes(video);
            // 音频转换格式类
            Encoder encoder = new Encoder();
            encoder.encode(mediaObject, target, attrs);
            System.out.println("转换已完成...");
        } catch (EncoderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量转化视频格式
     *
     * @param sourceFolderPath : 源视频文件夹路径
     * @param targetFolderPath : 目标gif文件夹路径
     * @return
     */
    public static void batchTransform(String sourceFolderPath, String targetFolderPath) {
        File sourceFolder = new File(sourceFolderPath);
        if (sourceFolder.list().length != 0) {
            Arrays.asList(sourceFolder.list()).forEach(e -> {
                transform(new VideoParam(sourceFolderPath + File.separator + e,
                        targetFolderPath + File.separator + getNewFileName(e),
                        0.2,
                        10));
            });
        }
    }

    public static class VideoParam {
        /**
         *  源视频文件路径
         */
        String sourceFolderPath;
        /**
         * 目标gif文件路径
         */
        String targetFolderPath;
        Double scale;
        Integer frameRate;

        public VideoParam(String sourceFolderPath, String targetFolderPath, Double scale, Integer frameRate) {
            this.sourceFolderPath = sourceFolderPath;
            this.targetFolderPath = targetFolderPath;
            this.scale = scale;
            this.frameRate = frameRate;
        }

        public String getSourceFolderPath() {
            return sourceFolderPath;
        }

        public String getTargetFolderPath() {
            return targetFolderPath;
        }

        public Double getScale() {
            return scale;
        }

        public Integer getFrameRate() {
            return frameRate;
        }
    }


    public static void main(String[] args) {
        batchTransform("D:/testV", "D:/testV");
    }


}
