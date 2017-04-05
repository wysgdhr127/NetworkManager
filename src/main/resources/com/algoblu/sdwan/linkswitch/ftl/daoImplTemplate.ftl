package ${defaultTemplate.packagePath};

import org.springframework.stereotype.Repository;
import ${defaultTemplate.baseDaoImplPath};
import ${defaultTemplate.daoPath};
import ${defaultTemplate.modelPath};

/**
 * 
 * @description 
 *
 * @author  wang yang
 */
@Repository
public class ${defaultTemplate.daoImplName} extends ${defaultTemplate.baseDaoImplName}<${defaultTemplate.entityName}> implements ${defaultTemplate.daoName} {
	
}