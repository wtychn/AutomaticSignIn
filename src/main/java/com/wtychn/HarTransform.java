package com.wtychn;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HarTransform {
    public String getParams(Har har) throws HarReaderException {
        return har.getLog()
                .getEntries()
                .get(0)
                .getRequest()
                .getPostData()
                .getText();
    }

    public Cookie getCookie(Har har) {
        String userCookies = har.getLog()
                .getEntries()
                .get(0)
                .getRequest()
                .getHeaders()
                .get(16)
                .getValue();
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
