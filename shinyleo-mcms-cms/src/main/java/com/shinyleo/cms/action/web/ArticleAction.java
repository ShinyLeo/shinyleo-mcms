
package com.shinyleo.cms.action.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shinyleo.basic.action.BaseAction;
import com.shinyleo.basic.biz.IColumnBiz;
import com.shinyleo.basic.entity.ColumnEntity;
import com.shinyleo.cms.biz.IArticleBiz;
import com.shinyleo.cms.entity.ArticleEntity;
import com.shinyleo.mdiy.biz.IContentModelBiz;
import com.shinyleo.mdiy.biz.IContentModelFieldBiz;
import com.shinyleo.mdiy.entity.ContentModelEntity;
import com.shinyleo.util.StringUtil;
import net.shinyleo.basic.util.BasicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 * 

 */
@Controller("jsonApiArticle")
@RequestMapping("/mcms/article")
public class ArticleAction extends BaseAction {

	/**
	 * 文章管理业务处理层
	 */
	@Autowired
	private IArticleBiz articleBiz;

	/**
	 * 栏目管理业务处理层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 内容模型管理业务处理层
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;

	/**
	 * 自定义字段管理业务处理层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;

	/**
	 * 文章信息
	 * 
	 * @param basicId
	 *            文章编号
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {"basicCategoryId":分类编号,basicTitle
	 *            :"标题",basicDescription:"描述",basicThumbnails:"缩略图",
	 *            basicDateTime:"发布时间",basicUpdateTime:"更新时间","basicHit":点击数,
	 *            "basicId":编号 articleContent:"文章内容","basicSort":排序,[自定义模型字段]}
	 */
	@RequestMapping("/{basicId}/detail")
	@ResponseBody
	public void detail(@PathVariable int basicId, HttpServletRequest request, HttpServletResponse response) {
		ArticleEntity article = articleBiz.getById(basicId);
		if (article == null) {
			this.outJson(response, "");
			return;
		}
		// 获取文章栏目id获取栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(article.getBasicCategoryId());
		ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
				.getEntity(column.getColumnContentModelId());

		// 判断内容模型的值
		if (contentModel != null) {
			Map where = new HashMap();
			// 压入basicId字段的值
			where.put("basicId", basicId);
			// 遍历所有的字段实体,得到字段名列表信息
			List<String> listFieldName = new ArrayList<String>();
			listFieldName.add("basicId");
			// 查询新增字段的信息
			List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
			if (fieldLists != null || fieldLists.size() > 0) {
				Map map = (Map) fieldLists.get(0);
				article.setExtendsFields(map);
			}
		}

		this.outJson(response, JSONObject.toJSONStringWithDateFormat(article, "yyyy-MM-dd hh:mm:ss"));
	}


	/**
	 * 文章列表信息
	 * 
	 * @param pageSize
	 *            一页显示数量
	 * @param pageNo
	 *            当前页码
	 * @param basicCategoryId
	 *            分类编号
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {count:"记录总数", list:
	 *            "[{basicTitle:"标题",basicDescription:"描述",basicThumbnails:"缩略图",basicDateTime:"发布时间",basicUpdateTime:"更新时间","basicHit":点击数,"basicId":编号}]"
	 *            }
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ArticleEntity article, HttpServletRequest request, HttpServletResponse response) {
		int pageNo = this.getPageNo(request);
		int pageSize = this.getPageSize(request);
		int categoryId = article.getBasicCategoryId();
		String isHasChilds = request.getParameter("isHasChilds"); // 是否取子类信息

		boolean _isHasChilds = false; // true取子栏目，false取当前栏目
		if (!StringUtil.isBlank(isHasChilds) && isHasChilds.equals("true")) {
			_isHasChilds = true;
		}
		int appId = BasicUtil.getAppId();
		int[] ids = null;
		if (article.getBasicCategoryId()>0) {
			 ids = new int[]{article.getBasicCategoryId()};
		}
		BasicUtil.startPage();
		List list = articleBiz.query(appId, ids, null, null, null, false, article);
		BasicUtil.endPage(list); 
		this.outJson(response, JSONArray.toJSONString(list));
	}

}