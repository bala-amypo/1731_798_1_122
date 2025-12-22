// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.RoiReport;
// import com.example.demo.model.DiscountCode;
// import com.example.demo.model.Influencer;
// import com.example.demo.model.Campaign;
// import com.example.demo.model.SaleTransaction;
// import com.example.demo.repository.RoiReportRepository;
// import com.example.demo.repository.DiscountCodeRepository;
// import com.example.demo.repository.SaleTransactionRepository;
// import com.example.demo.service.RoiService;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.util.List;

// @Service
// public class RoiServiceImpl implements RoiService {

//     private final RoiReportRepository roiReportRepository;
//     private final DiscountCodeRepository discountCodeRepository;
//     private final SaleTransactionRepository saleTransactionRepository;

//     public RoiServiceImpl(RoiReportRepository roiReportRepository,
//                          DiscountCodeRepository discountCodeRepository,
//                          SaleTransactionRepository saleTransactionRepository) {
//         this.roiReportRepository = roiReportRepository;
//         this.discountCodeRepository = discountCodeRepository;
//         this.saleTransactionRepository = saleTransactionRepository;
//     }

//     @Override
//     public RoiReport generateRoiForCode(Long codeId) {
//         DiscountCode discountCode = discountCodeRepository.findById(codeId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        
//         // Get all sales for this discount code
//         List<SaleTransaction> sales = saleTransactionRepository.findByDiscountCodeId(codeId);
        
//         // Calculate total sales
//         BigDecimal totalSales = BigDecimal.ZERO;
//         for (SaleTransaction sale : sales) {
//             totalSales = totalSales.add(sale.getSaleAmount());
//         }
        
//         // Calculate total revenue (sales after discount)
//         BigDecimal totalRevenue = BigDecimal.ZERO;
//         for (SaleTransaction sale : sales) {
//             BigDecimal discountMultiplier = BigDecimal.ONE
//                     .subtract(BigDecimal.valueOf(discountCode.getDiscountPercentage())
//                             .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
//             totalRevenue = totalRevenue.add(sale.getSaleAmount().multiply(discountMultiplier));
//         }
        
//         // Calculate ROI percentage
//         Campaign campaign = discountCode.getCampaign();
//         Influencer influencer = discountCode.getInfluencer();
        
//         // Calculate prorated budget for this discount code
//         long totalCodesInCampaign = discountCodeRepository.findByCampaignId(campaign.getId()).size();
//         BigDecimal proratedBudget = campaign.getBudget()
//                 .divide(BigDecimal.valueOf(Math.max(totalCodesInCampaign, 1)), 2, RoundingMode.HALF_UP);
        
//         BigDecimal roiPercentage = BigDecimal.ZERO;
//         if (proratedBudget.compareTo(BigDecimal.ZERO) > 0) {
//             roiPercentage = totalRevenue.subtract(proratedBudget)
//                     .divide(proratedBudget, 4, RoundingMode.HALF_UP)
//                     .multiply(BigDecimal.valueOf(100));
//         }
        
//         // Create and save ROI report
//         RoiReport roiReport = new RoiReport();
//         roiReport.setCampaign(campaign);
//         roiReport.setInfluencer(influencer);
//         roiReport.setTotalSales(totalSales);
//         roiReport.setTotalRevenue(totalRevenue);
//         roiReport.setRoiPercentage(roiPercentage.setScale(2, RoundingMode.HALF_UP));
        
//         return roiReportRepository.save(roiReport);
//     }

//     @Override
//     public RoiReport getReportById(Long id) {
//         return roiReportRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("ROI report not found"));
//     }

//     @Override
//     public List<RoiReport> getReportsForInfluencer(Long influencerId) {
//         return roiReportRepository.findByInfluencerId(influencerId);
//     }

//     @Override
//     public List<RoiReport> getReportsForCampaign(Long campaignId) {
//         return roiReportRepository.findByCampaignId(campaignId);
//     }
// }