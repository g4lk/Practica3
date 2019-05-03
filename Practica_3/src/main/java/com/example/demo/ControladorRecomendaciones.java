package com.example.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorRecomendaciones {

	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	@Autowired
	private RepositorioCliente rc;

	@GetMapping("/recomendaciones/{id}")
	public List<RecommendedItem> recomendaciones(@PathVariable int id) {

		List<RecommendedItem> recUsers = new ArrayList<>();
		UserSimilarity similarity = null;
		try {
			MysqlDataSource dataSource = new MysqlDataSource();

			dataSource.setServerName("localhost");
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setDatabaseName("practica3");
			dataSource.setServerTimezone("UTC");
			dataSource.setUseSSL(false);
			dataSource.setAllowPublicKeyRetrieval(true);

			DataModel model = new ReloadFromJDBCDataModel(
					new MySQLJDBCDataModel(dataSource, "compra", "id_cliente", "id_producto", "valoracion", null));

			similarity = new PearsonCorrelationSimilarity(model);
			ThresholdUserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recUsers = recommender.recommend(id, 3);
		} catch (TasteException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recUsers;
	}

	@GetMapping("/recomendaciones/")
	public List<List<RecommendedItem>> recomendaciones() {
		Iterable<Cliente> lc = rc.findAll();
		List<List<RecommendedItem>> llr = new ArrayList<List<RecommendedItem>>();
		for (Cliente c : lc) {
			llr.add(recomendaciones(c.getId_cliente()));
		}
		return llr;
	}
}
