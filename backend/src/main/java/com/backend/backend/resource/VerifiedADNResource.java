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
        List<String> tempRow = new ArrayList<String>();

        List<List<String>> formattedList = new ArrayList<List<String>>();
        for (String row : idList) {
            if (row.length() != 6) throw new InvalidSequenceException("6 columns are required for each row");
            tempRow = Arrays.asList(row.split(""));
            for (String colValue : tempRow) {
                if (!colValue.equals("A") && !colValue.equals("C") && !colValue.equals("G") && !colValue.equals("T"))
                    throw new InvalidSequenceException("Invalid character. Got " + colValue);
            }
            formattedList.add(tempRow);
        }

        Boolean correct = checkWin(formattedList);

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

    public static Boolean checkWin(List<List<String>> board) {
        final int HEIGHT = board.size();
        final int WIDTH = board.get(0).size();

        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                String base = board.get(r).get(c);
                if (c + 3 < WIDTH &&
                    base.equals(board.get(r).get(c+1)) && // look right
                    base.equals(board.get(r).get(c+2)) &&
                    base.equals(board.get(r).get(c+3)))
                    return false;
                if (r + 3 < HEIGHT) {
                    if (base.equals(board.get(r+1).get(c)) && // look up
                        base.equals(board.get(r+2).get(c)) &&
                        base.equals(board.get(r+3).get(c)))
                        return false;
                    if (c + 3 < WIDTH &&
                        base.equals(board.get(r+1).get(c+1)) && // look up & right
                        base.equals(board.get(r+2).get(c+2)) &&
                        base.equals(board.get(r+3).get(c+3)))
                        return false;
                    if (c - 3 >= 0 &&
                        base.equals(board.get(r+1).get(c+1)) && // look up & left
                        base.equals(board.get(r+2).get(c+2)) &&
                        base.equals(board.get(r+3).get(c+3)))
                        return false;
                }
            }
        }

        return true; // no winner found
    }
}
