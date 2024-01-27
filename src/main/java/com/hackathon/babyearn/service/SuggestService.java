package com.hackathon.babyearn.service;


import com.hackathon.babyearn.model.SavingModel;
import com.hackathon.babyearn.model.Suggest;
import com.hackathon.babyearn.model.Transaction;
import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.repository.SuggestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SuggestService {
    private final SuggestRepository suggestRepository;
    private TransactionService transactionService;
    private WalletService walletService;
    private SavingService savingService;


    public Suggest suggestFinancialEducation(Long userId) {
        List<Transaction> transactions = transactionService.getTransactionByUserId(userId);
        Wallet wallet = walletService.getWalletByUserId(userId);
        SavingModel saving = savingService.getSavingByUser_id(userId);
        Suggest actualSuggest = suggestRepository.findByUser_Id(userId);

        StringBuilder suggestion = new StringBuilder("🚀 Suggestion pour toi 🚀 :\n");

        if (wallet.getBalance() > 0) {
            suggestion.append("💰 Super! Tu as de l'argent dans ta tirelire. Pense à mettre une partie dans ton épargne pour de futurs projets excitants ! 💰\n");
        }

        int highExpenseCategoryCount = countHighExpenseCategories(transactions);
        Set<String> excess = HighExpenseCategories(transactions);
        if (highExpenseCategoryCount > 0) {
            suggestion.append("🎉 Wow ! Tu dépenses beaucoup dans certaines choses. Réfléchis à comment économiser pour des choses encore plus cool ! 🎉\n");
            suggestion.append("tu as depensé beaucoup plus sur : ").append(excess);
        }

        Set<SavingModel> getSaving = CompletedGoals(userId);
        if (getSaving.size() > 0) {
            suggestion.append("🌟 Youpi ! Tu as atteint certains de tes objectifs financiers. Continue comme ça et rêve grand ! 🌟\n");
        }
        actualSuggest.setDescription(suggestion.toString());

        return actualSuggest;

    }

    private int countHighExpenseCategories(List<Transaction> transactions) {

        final double HIGH_EXPENSE_THRESHOLD = 100.0;
        Set<String> highExpenseCategories = new HashSet<>();

        for (Transaction transaction : transactions) {
            if (transaction.getValue() > HIGH_EXPENSE_THRESHOLD) {
                highExpenseCategories.add(transaction.getCategory());
            }
        }
        return highExpenseCategories.size();
    }
    private Set<String> HighExpenseCategories(List<Transaction> transactions) {

        final double HIGH_EXPENSE_THRESHOLD = 12.0;

        Set<String> highExpenseCategories = new HashSet<>();

        for (Transaction transaction : transactions) {
            if (transaction.getValue() > HIGH_EXPENSE_THRESHOLD) {
                highExpenseCategories.add(transaction.getCategory());
            }
        }
        return highExpenseCategories;
    }
    private Set<SavingModel> CompletedGoals(Long userId){
        List<SavingModel> savingModels = savingService.getAllByStatus("ACHIEVED", userId);
        return new HashSet<>(savingModels);

    }
   // private int countCompletedGoals(List<Epargne> epargnes) {
        // Utiliser un filtre pour obtenir les épargnes avec le statut "ACHIEVED"
    // long completedGoalsCount = epargnes.stream()
        //        .filter(epargne -> "ACHIEVED".equals(epargne.getStatus()))
          //      .count();

        // Convertir le résultat en int (si nécessaire)
        //return Math.toIntExact(completedGoalsCount);
    //}

}
