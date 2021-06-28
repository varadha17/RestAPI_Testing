package pth.pojos;

import java.util.List;

import lombok.Data;

@Data
public class parentObject {

	private String status;
	private int count;
	private int count_total;
	private int pages;
	private List<posts> posts; 
	
		
}
