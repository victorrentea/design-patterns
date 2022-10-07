//package victor.training.patterns.visitor;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.advicecompletionfee.AdviceCompletionFeeCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.currentscenario.CompensationFeeResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.customerinterestrate.CustomerInterestRateCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.loantovalue.LoanToValueCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.marketvalue.nhg.NhgMarketValueCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.marketvalue.rabo.RaboMarketValueCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.maximumloan.MaximumLoanCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.maximumloanbasedoncollateral.MaximumLoanBasedOnCollateralCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.maximumloanbasedonincome.MaximumLoanBasedOnIncomeCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.maximumloanbasedonincomewithoutobligations.MaximumLoanBasedOnIncomeWithoutObligationsCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.mortgagefinancingresult.MortgageFinancingCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.nhgcheck.NhgCheckCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.ownmoney.OwnMoneyCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.productarrangement.ExternalRefinanceProductArrangementsResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.refinance.RefinancingCostsCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.valuedcapital.ValuedCapitalCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.scenario.common.step.productarrangements.result.ProductArrangementsResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.scenario.homefinancing.advice.step.financingrequirement.result.FinancingRequirementCalculationResult;
//import nl.rabobank.gict.fpx.bwf.service.core.calculate.scenario.homefinancing.orientation.increasefinance.model.result.calculation.obligationstatement.ObligationStatementCalculationResult;
///** * @deprecated Will be replaced with the CalculationOutcomeModelMapper.mapCauses function. */@Deprecated(since="2021-05-03", forRemoval=true)
//public interface CalculationResultVisitor {
//    <R> R visit(
//            nl.rabobank.gict.fpx.bwf.service.core.calculate.scenario.homefinancing.orientation.increasefinance.model.result.calculation.financingrequirement.FinancingRequirementCalculationResult financingRequirementCalculationResult);
//    <R> R visit(final AdviceCompletionFeeCalculationResult adviceCompletionFeeCalculationResult);
//    <R> R visit(final CustomerInterestRateCalculationResult customerInterestRateCalculationResult);
//    <R> R visit(final CompensationFeeResult compensationFeeResult);
//    <R> R visit(final FinancingRequirementCalculationResult financingRequirementScenarioCalculationResult);
//    <R> R visit(final LoanToValueCalculationResult loanToValueCalculationResult);
//    <R> R visit(final MaximumLoanBasedOnCollateralCalculationResult maximumLoanBasedOnCollateralCalculationResult);
//    <R> R visit(final MaximumLoanBasedOnIncomeCalculationResult maximumLoanBasedOnIncomeCalculationResult);
//    <R> R visit(final MaximumLoanBasedOnIncomeWithoutObligationsCalculationResult maximumLoanBasedOnIncomeWithoutObligationsCalculationResult);
//    <R> R visit(final MaximumLoanCalculationResult maximumLoanCalculationResult);
//    <R> R visit(final MortgageFinancingCalculationResult mortgageFinancingCalculationResult);
//    <R> R visit(final NhgCheckCalculationResult nhgCheckCalculationResult);
//    <R> R visit(final NhgMarketValueCalculationResult nhgMarketValueCalculationResult);
//    <R> R visit(final nl.rabobank.gict.fpx.bwf.service.core.calculate.common.model.result.calculation.obligationstatement.ObligationStatementCalculationResult obligationStatementCalculationResult);
//    <R> R visit(final ObligationStatementCalculationResult obligationStatementCalculationResult);
//    <R> R visit(final OwnMoneyCalculationResult ownMoneyCalculationResult);
//    <R> R visit(final ExternalRefinanceProductArrangementsResult externalRefinanceProductArrangementsResult);
//    <R> R visit(final ProductArrangementsResult productArrangementsResult);
//    <R> R visit(final RaboMarketValueCalculationResult raboMarketValueCalculationResult);
//    <R> R visit(final RefinancingCostsCalculationResult refinancingCostsCalculationResult);
//    <R> R visit(final ValuedCapitalCalculationResult valuedCapitalCalculationResult);
//}