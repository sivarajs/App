package i2par.placement.student;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import meru.image.ImageSystem;
import meru.sys.IOSystem;

public class StudentImageRepository {

    public static final String IMG_ROOT = "img/student/";
    public static final String TN_NAME = "tn.jpg";
    private static final String NO_IMAGE = "no-image.jpg";

    private static final byte BUCKET_SIZE = 100;

    private File mImageHome;
    private String mImgRoot;

    private int mThumbnailWidth;
    private int mThumbnailHeight;

    public StudentImageRepository(String appName,
                                  int thumbnailWidth,
                                  int thumbnailHeight) {

        if (appName != null) {
            appName = appName + "/";
        }
        else {
            appName = "/";
        }

        mImgRoot = appName + IMG_ROOT;

        mImageHome = new File("webapps" + mImgRoot);
        mImageHome.mkdirs();
        mThumbnailWidth = thumbnailWidth;
        mThumbnailHeight = thumbnailHeight;

    }

    public final String getNoImage() {
        return mImgRoot + NO_IMAGE;
    }

    public static int getImageBucket(long productId) {
        return (int) (productId % BUCKET_SIZE);
    }

    private File storeImage(File targetFolder,
                            File imageFile,
                            String fileName,
                            boolean isThumbnail) {

        targetFolder.mkdirs();

        try {

            if (isThumbnail) {
                File tnFile = new File(targetFolder,
                                       TN_NAME);

                if (tnFile.exists()) {
                    tnFile.delete();
                }

                BufferedImage bufferedImage = ImageIO.read(imageFile);

                int width = bufferedImage.getWidth();

                float widthPer = 0F;
                if (width > mThumbnailWidth) {
                    widthPer = (float) mThumbnailWidth / width;
                }

                int height = bufferedImage.getHeight();
                float heightPer = 0F;
                if (height > mThumbnailHeight) {
                    heightPer = (float) mThumbnailHeight / height;
                }

                float percent = 0F;
                if (widthPer == 0F && heightPer == 0F) {
                    percent = 0;
                }

                else if (widthPer == 0F) {
                    percent = heightPer;
                }
                else if (heightPer == 0F) {
                    percent = widthPer;
                }
                else {
                    percent = (widthPer < heightPer) ? widthPer : heightPer;
                }

                if (percent > 0) {

                    width = Math.round(width * percent);
                    height = Math.round(height * percent);

                }

                ImageSystem.resize(imageFile,
                                   tnFile,
                                   width,
                                   height);

            }

            if (fileName == null) {
                fileName = imageFile.getName();
            }
            else {
                fileName = fileName + ".jpg";
            }

            File targetFile = new File(targetFolder,
                                       fileName);
            imageFile.renameTo(targetFile);
            return targetFile;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getCampusRelativePath(long campusId) {
        return mImgRoot + getImageBucket(campusId) + "/" + campusId + "/";
    }

    public String getStudentImageRelativePath(long campusId,
                                              long studentId) {

        File imgFolder = getStudentImageFolder(campusId,
                                               studentId);

        File[] fileNames = imgFolder.listFiles();

        if (fileNames == null || fileNames.length == 0) {
            return getNoImage();
        }

        String fileName = fileNames[0].getName();

        return getCampusRelativePath(campusId) + studentId + "/" + fileName;
    }

    private File getCampusFolder(long campusId) {
        int bucket = getImageBucket(campusId);
        File targetFolder = new File(mImageHome,
                                     String.valueOf(bucket));
        targetFolder = new File(targetFolder,
                                String.valueOf(campusId));

        return targetFolder;
    }

    private File getStudentImageFolder(long campusId,
                                       long studentId) {
        File campusFolder = getCampusFolder(campusId);
        File studentImgFolder = new File(campusFolder,
                                         String.valueOf(studentId));
        return studentImgFolder;
    }

    public File storeImage(StudentImage studentImage) {

        File imageFile = new File(studentImage.getImage());
        File imgFolder = getStudentImageFolder(studentImage.getCampusId(),
                                               studentImage.getStudentId());

        if (imgFolder.exists()) {

            IOSystem.delete(imgFolder);
        }

        File targetFile = storeImage(imgFolder,
                                     imageFile,
                                     null,
                                     false);

        String path = targetFile.getAbsolutePath()
                                .replace('\\',
                                         '/');
        path = path.substring(path.indexOf(mImgRoot));
        studentImage.setId(path);
        studentImage.setImage(path);
        return targetFile;

    }

    public void deleteImage(String id) {
        String[] parts = id.split("/");
        File imgDir = getStudentImageFolder(Long.parseLong(parts[0]),
                                            Long.parseLong(parts[1]));
        File imgFile = new File(imgDir,
                                parts[2]);
        imgFile.delete();
    }
}
