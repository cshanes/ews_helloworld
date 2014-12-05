package org.spectrumhealth.ws.helloworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.spectrumhealth.ws.helloworld.annotation.ContractDataSource;
import org.spectrumhealth.ws.helloworld.data.ContractData;

import org.spectrumhealth.ws.helloworld.data.ContractData;
import com.priorityhealth.ws.enterprise.base.model.DateRange;
import com.priorityhealth.ws.enterprise.jdbc.PhJdbcTemplate;
import com.priorityhealth.ws.enterprise.util.ContractIdUtility;

public class JdbcContractDao
{

	private PhJdbcTemplate jdbcTemplate;

	@Inject
	public void setDataSource( @ContractDataSource DataSource dataSource )
	{
		jdbcTemplate = new PhJdbcTemplate(dataSource);
	}

	public List<ContractData> findContractByContractExtId( ContractIdUtility sampleId, DateRange dateRange )
	{
		String query = buildContractQuery();

		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("sbsbId", sampleId.getSbsbId());
		parms.put("memeSfx", sampleId.getMemeSfx());
		parms.put("startDate", dateRange.getFromDate());
		parms.put("endDate", dateRange.getToDate());
		return jdbcTemplate.query(query, parms, new BeanPropertyRowMapper<ContractData>(ContractData.class));
	}

	/* ============================================================ */

	private String buildContractQuery()
	{
		return buildContractQuery("");
	}
	
	private String buildContractQuery( String additionalWhereClause )
	{
		// @formatter:off
		return  "SELECT sbsb.sbsb_ck,  " +
				"sbsb.sbsb_id,  " +
				"sbsb.sbsb_title,  " +
				"sbsb.sbsb_first_name,  " +
				"sbsb.sbsb_mid_init,  " +
				"sbsb.sbsb_last_name,  " +
				"trim(sbsb_meme.meme_record_no) sbsb_record_no,  " +
				"trim(meme.meme_record_no) meme_record_no,  " +
				"meme.meme_rel,  " +
				"meme.meme_ck,  " +
				"meme.meme_title,  " +
				"meme.meme_first_name,  " +
				"meme.meme_mid_init,  " +
				"meme.meme_last_name,  " +
				"meme.meme_orig_eff_dt,  " +
				"meme.grgr_ck,  " +
				"meme.meme_mctr_sts,  " +
				"meme.meme_sfx,  " +
				"mepe.sgsg_ck,  " +
				"mepe.cspd_cat,  " +
				"mepe.mepe_eff_dt,  " +
				"mepe.mepe_term_dt,  " +
				"mepe.cscs_id,  " +
				"mepe.cspi_id,  " +
				"mepe.pdpd_id,  " +
				"mepr.prpr_id,  " +
				"mepr.mepr_eff_dt,  " +
				"grgr.grgr_name,  " +
				"grgr.grgr_renew_mmdd,  " +
				"fsa.sbfs_ee_pldg_amt,  " +
				"fsa.sbfs_er_pldg_amt,  " +
				"fsa.sbfs_ee_cntrb_ytd,  " +
				"fsa.sbfs_er_cntrb_ytd,  " +
				"fsa.sbfs_paid_ytd_amt,  " +
				"fsa.sbfs_balance_amt,  " +
				"fsa.sbfs_eff_dt,  " +
				"fsa.sbfs_term_dt,  " +
				"lobd.lobd_id,  " +
				"lobd.lobd_name,  " +
				"lobd.lobd_mctr_type,  " +
				"(SELECT mctr_desc from cmc_mctr_cd_trans where mctr_value = lobd.lobd_mctr_type  " +
				"AND mctr_entity  = 'LOBD' AND mctr_type  = 'TYPE') lobd_mctr_desc,  " +
				"(SELECT pdds_desc from cmc_pdds_prod_desc where pdpd_id = mepe.pdpd_id ) pdds_desc,  " +
				"(SELECT prpr_name from cmc_prpr_prov where prpr_id = mepr.prpr_id) prpr_name,  " +
				"(SELECT sgsg_name from cmc_sgsg_sub_group WHERE sgsg_ck = mepe.sgsg_ck) sgsg_name,  " +
				"(SELECT cspd_cat_desc FROM cmc_cspd_desc WHERE cspd_cat = mepe.cspd_cat) cspd_cat_desc,  " +
				"(SELECT mctr_desc from cmc_mctr_cd_trans where mctr_value = meme.meme_mctr_sts  " +
				"AND mctr_entity  = 'MEME' AND mctr_type  = 'STS') meme_mctr_desc  " +
				"FROM cmc_sbsb_subsc sbsb,  " +
				"cmc_meme_member meme,  " +
				"cmc_meme_member sbsb_meme,  " +
				"cmc_grgr_group grgr, " +
				"cmc_mepe_prcs_elig mepe,  " +
				"cmc_mepr_prim_prov mepr,  " +
				"cmc_lobd_line_bus lobd,  " +
				"cmc_pdpd_product pdpd,  " +
				"cmc_sbfs_fsa_accum fsa  " +
				"WHERE sbsb.sbsb_id = :sbsbId " +
				"AND meme.meme_sfx = :memeSfx " +
				"AND sbsb.sbsb_ck = meme.sbsb_ck  " +
				"AND sbsb.sbsb_ck = sbsb_meme.sbsb_ck  " +
				"AND sbsb_meme.meme_rel = 'M'  " +
				"AND pdpd.pdpd_id = mepe.pdpd_id  " +
				"AND pdpd.lobd_id = lobd.lobd_id  " +
				"AND pdpd.pdpd_eff_dt <= mepe.mepe_term_dt  " +
				"AND pdpd.pdpd_term_dt >= mepe.mepe_eff_dt  " +
				"AND meme.grgr_ck = grgr.grgr_ck  " +
				"AND mepe.meme_ck = meme.meme_ck  " +
				"AND mepe.mepe_elig_ind = 'Y'  " +
				"AND mepr.meme_ck (+) = meme.meme_ck  " +
				"AND mepr.mepr_eff_dt (+) <= :endDate " +
				"AND mepr.mepr_term_dt (+) >= :startDate " +
				"AND sbsb.sbsb_ck  = fsa.sbsb_ck (+)  " +
				"AND mepe.mepe_eff_dt <= :endDate " +
				"AND mepe.mepe_term_dt >= :startDate " +
				"" + additionalWhereClause + " " +
				"ORDER BY sbsb_id, mepe_eff_dt, cspd_cat, mepr_eff_dt desc";
		// @formatter:on

	}
}
