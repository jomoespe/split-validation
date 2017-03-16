package com.jomoespe.lab.validationresponsability.validation;

import java.util.Objects;
import java.util.function.Predicate;

public final class Validation {
    private final boolean passed;
    private final float   score;
    // TODO Add validation messages list
    private final int     hashCode;
    
    private Validation(final boolean passed, final float score) {
        this.passed   = passed;
        this.score    = score;
        this.hashCode = calculateHashCode(); // precomputed hashcode value
    }

    public static Validation ok() {
        return new Validation.Builder().passed(true).score(1).build();
    }
    
    public static Validation fail(final String...messages) {
        return new Validation.Builder().passed(false).score(0).build();
    }

    public static Builder from(final Validation validation) {
        return new Validation.Builder()
                .passed(validation.passed)
                .score(validation.score);
    }
    
    public boolean passed() { 
        return passed; 
    }
    
    public float score()  { 
        return score; 
    }
    
    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                         { return true; }
        if (obj == null)                         { return false; }
        if (getClass() != obj.getClass())        { return false; }
        final Validation other = (Validation) obj;
        if (passed != other.passed)              { return false; }
        if (!Objects.equals(score, other.score)) { return false; }
        return hashCode == other.hashCode;
    }
    
    private int calculateHashCode() {
        int hash = 3;
        hash = 79 * hash + (passed ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(score);
        return hash;
    }

    public static class Builder {
        private boolean passed = true;
        private float   score  = 1f;
    
        private final Predicate<Float> validScore = (aScore) -> aScore>=0 && aScore<=1;
        
        public Builder passed(final boolean passed) {
            this.passed = passed;
            return this;
        }

        public Builder score(final float score) {
            assert validScore.test(score) : "Not valid score. Should be a value between 0 and 1 and is " + score;
            if (validScore.test(score)) {
                this.score = score;
            }
            return this;
        }
        
        public Builder maxScore() {
            return score(1);
        }

        public Builder minScore() {
            return score(0);
        }
        
        public Validation build() {
            return new Validation(passed, score);
        }
    }
}
