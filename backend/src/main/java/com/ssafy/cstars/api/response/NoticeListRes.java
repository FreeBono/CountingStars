package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.Notice;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("NoticeListResponse")
public class NoticeListRes {

    public static Page<NoticeRes> of(Page<Notice> noticeList) {
        List<NoticeRes> list = new ArrayList<>();

        Pageable pageable = noticeList.getPageable();
        long total = noticeList.getTotalElements();

        noticeList.forEach( notice -> {
            list.add(NoticeRes.of(notice));
        });

        return new PageImpl<NoticeRes>(list, pageable, total);
    }
}
