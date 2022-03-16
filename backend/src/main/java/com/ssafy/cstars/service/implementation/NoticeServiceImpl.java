package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.api.request.NoticeDeleteReq;
import com.ssafy.cstars.api.request.NoticePostReq;
import com.ssafy.cstars.api.request.NoticePutReq;
import com.ssafy.cstars.domain.entity.Admin;
import com.ssafy.cstars.domain.entity.Notice;
import com.ssafy.cstars.domain.repository.NoticeRepository;
import com.ssafy.cstars.domain.repository.NoticeRepositorySupport;
import com.ssafy.cstars.service.AdminService;
import com.ssafy.cstars.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    AdminService adminService;

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeRepositorySupport noticeRepositorySupport;

    @Override
    public Page<Notice> getNoticeList(Pageable pageable) {
        Page<Notice> notices = noticeRepositorySupport.findAll(pageable);

        if(notices.isEmpty()) return null;

        return notices;
    }

    @Override
    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElse(null);
    }

    @Override
    public int createNotice(NoticePostReq noticeInfo) {
        Admin admin = adminService.getAdmin(noticeInfo.getAdminId());

        // 유저 정보 없거나 운영자가 아닐 경우
        if( admin == null )
            return 401;

        Notice notice = Notice.builder()
                .admin(admin)
                .title(noticeInfo.getTitle())
                .content(noticeInfo.getContent())
                .build();

        System.out.println(notice);

        if(notice == null) return 500;

        noticeRepository.save(notice);

        return 200;
    }

    @Override
    public int modifyNotice(NoticePutReq noticeInfo) {
        Admin admin = adminService.getAdmin(noticeInfo.getAdminId());

        // 유저 정보 없거나 운영자가 아닐 경우
        if( admin == null )
            return 401;

        Notice notice = getNotice(noticeInfo.getNoticeId());

        if(notice == null) return 500;

        notice.modify(noticeInfo.getTitle(), noticeInfo.getContent());
        notice.setModifiedDate();
        noticeRepository.save(notice);

        return 200;
    }

    @Override
    public int deleteNotice(NoticeDeleteReq noticeInfo) {
        Admin admin = adminService.getAdmin(noticeInfo.getAdminId());

        // 유저 정보 없거나 운영자가 아닐 경우
        if( admin == null )
            return 401;

        Notice notice = getNotice(noticeInfo.getNoticeId());

        if(notice == null) return 500;

        noticeRepository.delete(notice);

        return 200;
    }
}
