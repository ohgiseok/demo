package com.example.demo.jobs.dormantmail.model.domain;

import com.example.demo.jobs.dormantmail.model.domain.enums.NotiChannel;
import com.example.demo.jobs.dormantmail.model.domain.enums.NotiState;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TB_DORMANT_NOTI")
public class DormantNoti {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "SEQNO")
    private Integer seqNo;

    @Column(name = "INUSERID")
    private String inUserId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "USERPHONE")
    private String userPhone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DORMANT_DATE")
    private LocalDate dormantDate;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private NotiState state;

    @Column(name = "CHANNEL")
    @Enumerated(EnumType.STRING)
    private NotiChannel channel;

    @Column(name = "NOTI_DATE")
    private LocalDate notiDate;

    @Column(name = "INDATE")
    private LocalDateTime inDate;

    @Builder
    public DormantNoti(String inUserId, String userName, String userPhone, String email, LocalDate dormantDate, NotiState state, NotiChannel channel, LocalDate notiDate, LocalDateTime inDate){
        this.inUserId = inUserId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.email = email;
        this.dormantDate = dormantDate;
        this.state = state;
        this.channel = channel;
        this.notiDate = notiDate;
        this.inDate = inDate;
    }

    @Override
    public String toString() {
        return "DormantNoti{" +
                "seqNo=" + seqNo +
                ", inUserId='" + inUserId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", email='" + email + '\'' +
                ", dormantDate=" + dormantDate +
                ", state=" + state +
                ", channel=" + channel +
                ", notiDate=" + notiDate +
                ", inDate=" + inDate +
                '}';
    }
}
