package nvt.st.T.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{
    @Value("${cloudinary.folderName}")
    private String rootFolder;
    @Autowired
    private Cloudinary cloudinary;

//    @Override
//    public List<String> upload(List<MultipartFile> files, String folderName) {
//        List<String> urls = new ArrayList<>();
//        try{
//            for (MultipartFile file : files) {
//                Map params = ObjectUtils.asMap(
//                        "use_filename", true,
//                        "unique_filename", true,
//                        "folder", rootFolder + "/" + folderName
//                );
//                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
//                urls.add(uploadResult.get("url").toString());
//            }
//            return urls;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Override
    public List<String> upload(List<MultipartFile> files, String folderName) {
        List<String> urls = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                Map<String, String> params = ObjectUtils.asMap(
                        "use_filename", true,
                        "unique_filename", true,
                        "folder", rootFolder + "/" + folderName
                );

                // Thực hiện tải lên dựa trên loại tệp
                Map uploadResult;
                if (file.getContentType().startsWith("image")) {
                    uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
                } else {
                    // Tạo một Map mới chứa các tham số từ params và resource_type được merge vào
                    Map<String, String> mergedParams = new HashMap<>(params);
                    mergedParams.put("resource_type", "auto");
                    uploadResult = cloudinary.uploader().upload(file.getBytes(), mergedParams);
                }

                urls.add(uploadResult.get("url").toString());
            }
            return urls;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Boolean delete(String imgUrl) {
        try{
            int rootFolderIndex = imgUrl.indexOf(rootFolder);
            int lastDotIndex = imgUrl.lastIndexOf(".");
            String publicId = imgUrl.substring(rootFolderIndex, lastDotIndex);
            Map options = Map.of("invalidate", true);
            Map result = cloudinary.uploader().destroy(publicId, options);
            return result.get("result").toString().equals("ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(List<String> imgUrls) {
        try {
            for (String imgUrl : imgUrls) {
                int rootFolderIndex = imgUrl.indexOf(rootFolder);
                int lastDotIndex = imgUrl.lastIndexOf(".");
                String publicId = imgUrl.substring(rootFolderIndex, lastDotIndex);
                Map options = Map.of("invalidate", true);
                Map result = cloudinary.uploader().destroy(publicId, options);
                if (!result.get("result").toString().equals("ok")) {
                    return false; // Nếu có bất kỳ lỗi nào, trả về false ngay lập tức
                }
            }
            return true; // Nếu không có lỗi, trả về true
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
