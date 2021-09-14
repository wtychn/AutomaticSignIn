package com.wtychn;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HarTransform {
    public String getParams(Har har) {
        return har.getLog()
                .getEntries()
                .get(0)
                .getRequest()
                .getPostData()
                .getText();
    }

    public String getURL(Har har) {
        return har.getLog()
                .getEntries()
                .get(0)
                .getRequest()
                .getUrl();
    }

    public Cookie getCookie(Har har) {
        List<HarHeader> headers = har.getLog()
                .getEntries()
                .get(0)
                .getRequest()
                .getHeaders();
        String userCookies = "";
        for (HarHeader header : headers) {
            String name = header.getName();
            if (name.equals("Cookie")) {
                userCookies = header.getValue();
            }
        }
        Cookie res = new Cookie();
        res.setUserCookie(userCookies);
        return res;
    }

    public List<Har> readHars(String path) throws HarReaderException {
        List<Har> harList = new ArrayList<>();
        File file = new File(path);
        File[] fileList = file.listFiles();

        assert fileList != null;
        for (File har : fileList) {
            // 文件类型判断
            if (har.isFile() && har.getName().endsWith(".har")) {
                harList.add(readHar(har));
            }
        }
        return harList;
    }

    private Har readHar(File file) throws HarReaderException {
        HarReader harReader = new HarReader();
        return harReader.readFromFile(file);
    }
}
