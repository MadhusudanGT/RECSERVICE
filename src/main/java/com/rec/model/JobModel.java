package com.rec.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jobs")
public class JobModel {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@SequenceGenerator(name="REC", sequenceName="id_seqence")
	private Long Id;
	
	@NotNull
	private Date DatePublished;
    
	@NotNull
	private String Description;

	@NotNull
	private String Name;
	
	@NotNull
//	@Temporal(value=TemporalType.TIMESTAMP)
	private Date StartingDate;
    
	
	@NotNull
//	@Temporal(value=TemporalType.TIMESTAMP)
	private Date EndingDate;
    
	@NotNull
	private String Vacancy;
    
	@OneToMany(targetEntity = ApplicationModel.class,mappedBy="jobs",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<ApplicationModel> application1=new ArrayList<>();
	

//	@ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = JobPlatform.class)
//    @JoinColumn(foreignKey = @ForeignKey(name = "jobPlatformId"), name = "jobPlatformId")
	@ManyToOne(cascade = CascadeType.ALL)
    private JobPlatform jobPlatform;
    
public JobPlatform getJobPlatform() {
		return jobPlatform;
	}
	public void setJobPlatform(JobPlatform jobPlatform) {
		this.jobPlatform = jobPlatform;
	}
	public JobPosition getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}
	public OrganizationModel getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	//    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = JobPosition.class)
//    @JoinColumn(foreignKey = @ForeignKey(name = "jobPositionId"), name = "jobPositionId")
	@ManyToOne(cascade = CascadeType.ALL)
    private JobPosition jobPosition;
    
    
//    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = OrganizationModel.class)
//    @JoinColumn(foreignKey = @ForeignKey(name = "organizationid"), name = "organizationid")
	@ManyToOne(cascade = CascadeType.ALL)
    private OrganizationModel organization;
	public JobModel() {
		super();
		}
	public List<ApplicationModel> getApplication1() {
		return application1;
	}
	public void setApplication1(List<ApplicationModel> application1) {
		this.application1 = application1;
	}
	public JobModel(Long id, @NotNull Date datePublished, @NotNull String description, @NotNull String name,
			@NotNull Date startingDate, @NotNull Date endingDate, @NotNull String vacancy) {
		super();
		Id = id;
		DatePublished = datePublished;
		Description = description;
		Name = name;
		StartingDate = startingDate;
		EndingDate = endingDate;
		Vacancy = vacancy;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDatePublished() {
		return DatePublished;
	}

	public void setDatePublished(Date datePublished) {
		DatePublished = datePublished;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getStartingDate() {
		return StartingDate;
	}

	public void setStartingDate(Date startingDate) {
		StartingDate = startingDate;
	}

	public Date getEndingDate() {
		return EndingDate;
	}

	public void setEndingDate(Date endingDate) {
		EndingDate = endingDate;
	}

	public String getVacancy() {
		return Vacancy;
	}

	public void setVacancy(String vacancy) {
		Vacancy = vacancy;
	}
   
	
	
}
