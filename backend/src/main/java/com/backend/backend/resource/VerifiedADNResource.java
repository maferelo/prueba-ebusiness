package com.backend.backend.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.backend.model.VerifiedADN;
import com.backend.backend.service.VerifiedADNService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.backend.backend.exception.InvalidSequenceException;

@RestController
@RequestMapping("/verifiedADN")
public class VerifiedADNResource {
    private final VerifiedADNService verifiedADNService;

    public VerifiedADNResource(VerifiedADNService verifiedADNService) {
        this.verifiedADNService = verifiedADNService;
    }

    @GetMapping("/all")
    public ResponseEntity <List<VerifiedADN>> getAllVerifiedADNs () {
        List<VerifiedADN> verifiedADNs = verifiedADNService.findAllVerifiedADNs();
        return new ResponseEntity<>(verifiedADNs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addVerifiedADN(@RequestBody ObjectNode objectNode) {
        // Check if sequence is empty
        if (objectNode.isEmpty()) throw new InvalidSequenceException("No ADN sequence in Body");
        
        String id = objectNode.get("id").toString().replace("\"", "").replace("[", "").replace("]", "");
        List<String> idList = new ArrayList<String>(Arrays.asList(id.split(",")));

        // Check number of rows
        if (idList.size() != 6) throw new InvalidSequenceException("6 rows are required. Got " + idList.size());

        for (String row : idList) {
            if (row.length() != 6) throw new InvalidSequenceException("6 columns are required for each row");
        }

        int[][] board = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
        Boolean correct = checkWin(board);

        VerifiedADN newVerifiedADN = new VerifiedADN();
        newVerifiedADN.setId(id);
        newVerifiedADN.setCorrect(correct);
        newVerifiedADN = verifiedADNService.addVerifiedADN(newVerifiedADN);
        
        return new ResponseEntity<>(correct, HttpStatus.CREATED);
    }

    @PostMapping("/statistics")
    public ResponseEntity<Map<String, String>> findVerifiedADNStatistics() {
        Map<String, String> statistics = verifiedADNService.findVerifiedADNStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.ACCEPTED);
    }

    public static Boolean checkWin(int[][] board) {
        final int HEIGHT = board.length;
        final int WIDTH = board[0].length;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = board[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots
    
                if (c + 3 < WIDTH &&
                    player == board[r][c+1] && // look right
                    player == board[r][c+2] &&
                    player == board[r][c+3])
                    return true;
                if (r + 3 < HEIGHT) {
                    if (player == board[r+1][c] && // look up
                        player == board[r+2][c] &&
                        player == board[r+3][c])
                        return true;
                    if (c + 3 < WIDTH &&
                        player == board[r+1][c+1] && // look up & right
                        player == board[r+2][c+2] &&
                        player == board[r+3][c+3])
                        return true;
                    if (c - 3 >= 0 &&
                        player == board[r+1][c-1] && // look up & left
                        player == board[r+2][c-2] &&
                        player == board[r+3][c-3])
                        return true;
                }
            }
        }
        return false; // no winner found
    }
}
