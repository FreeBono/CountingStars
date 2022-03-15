package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("NoticeResponse")
public class NoticeRes {
    @ApiModelProperty(name = "Notice id", notes = "Unique identifier of the Contact.",
            example = "1", required = true, position = 0)
    Long noticeId;
    @ApiModelProperty(name = "Notice title")
    String title;
    @ApiModelProperty(name = "Notice content")
    String content;
    @ApiModelProperty(name = "Notice date")
    LocalDateTime date;

    public static NoticeRes of(Notice notice) {
        NoticeRes res = new NoticeRes();

        res.setNoticeId(notice.getId());
        res.setTitle(notice.getTitle());
        res.setContent(notice.getContent());

        LocalDateTime modifyDate = notice.getModifyDate();
        res.setDate( modifyDate != null ? modifyDate.plusHours(9) : notice.getRegisterDate().plusHours(9) );

        return res;
    }
}
