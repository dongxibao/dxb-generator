package top.dxb.entity.sys;

import lombok.Data;

import java.util.Date;

/**
 * 系统配置
 * 
 * @author Dongxibao
 * @date 2020-12-24
 */
@Data
public class SysConfig {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/**  */
	private String configCode;
	/** 包名 */
	private String packageName;
	/** 模块名 */
	private String moduleName;
	/** 作者名称 */
	private String author;
	/** 邮箱 */
	private String email;
	/** 去除表前缀 */
	private String tablePrefix;
	/**  */
	private Date createTime;
	/**  */
	private Date updateTime;

	/** 是否使用(1是;0否) */
	private Integer beUse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SysConfig{" +
				"id=" + id +
				", configCode='" + configCode + '\'' +
				", packageName='" + packageName + '\'' +
				", moduleName='" + moduleName + '\'' +
				", author='" + author + '\'' +
				", email='" + email + '\'' +
				", tablePrefix='" + tablePrefix + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
