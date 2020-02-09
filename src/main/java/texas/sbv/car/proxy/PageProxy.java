package texas.sbv.car.proxy;


import org.springframework.stereotype.Component;

import lombok.Data;

import java.util.List;

@Data
@Component("pager")
public class PageProxy{
	private int totalCount, startRow, endRow, 
				pageCount,pageNum, pageSize, startPage, endPage,
				blockCount, blockNum, nextBlock, prevBlock;

	
	public void paging(List<?> list) {
		totalCount = list.size();
		pageCount = (totalCount % pageSize != 0) ? (totalCount / pageSize)+1 : totalCount / pageSize;
		startRow = (pageNum-1)*pageSize;
		endRow = (pageNum==pageCount) ? totalCount -1 : startRow + pageSize -1;
		
	}
	
	
}