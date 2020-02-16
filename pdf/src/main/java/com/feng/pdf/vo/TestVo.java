package com.feng.pdf.vo;

import com.feng.pdf.bean.Test;

public class TestVo  extends Test {

    private Integer allowPreview = null;

    private Integer allowDownload = null;

    private Integer allowEdit = null;

    public Integer getAllowPreview() {
        return allowPreview;
    }

    public void setAllowPreview(Integer allowPreview) {
        this.allowPreview = allowPreview;
    }

    public Integer getAllowDownload() {
        return allowDownload;
    }

    public void setAllowDownload(Integer allowDownload) {
        this.allowDownload = allowDownload;
    }

    public Integer getAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(Integer allowEdit) {
        this.allowEdit = allowEdit;
    }

    public TestVo() {
        super();
    }

    public TestVo(Integer allowPreview, Integer allowDownload, Integer allowEdit) {
        super();
        this.allowPreview = allowPreview;
        this.allowDownload = allowDownload;
        this.allowEdit = allowEdit;
    }
}
