package com.rave.pads.model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Comparable<Event>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6233460209615192637L;
	private String remark;
	private Date createDate;
	private String createdBy;
	
	
    public int compareTo(Event compareEvent) 
    {
        Date compareDate = compareEvent.getCreateDate();

        //ascending order
        //return this.createdDate - compareCreateDate;

        //descending order
        if (compareDate.after(this.createDate))
        {
            return -1;
        }
        if (compareDate.before(this.createDate))
        {
            return 1;
        }
        return 0;
    }
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
