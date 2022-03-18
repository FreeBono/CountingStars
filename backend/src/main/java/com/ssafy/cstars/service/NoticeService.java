package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.NoticeDeleteReq;
import com.ssafy.cstars.api.request.NoticePostReq;
import com.ssafy.cstars.api.request.NoticePutReq;
import com.ssafy.cstars.domain.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    Page<Notice> getNoticeList(Pageable pageable);
    Notice getNotice(Long noticeId);
    int createNotice(NoticePostReq noticeInfo);
    int modifyNotice(NoticePutReq noticeInfo);
    int deleteNotice(NoticeDeleteReq noticeInfo);
}
