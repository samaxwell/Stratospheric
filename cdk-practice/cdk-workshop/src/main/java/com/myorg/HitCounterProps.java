package com.myorg;

import software.amazon.awscdk.services.lambda.IFunction;

public interface HitCounterProps {

    static Builder builder() {
        return new Builder();
    }

    // the function for which we want to count url hits
    IFunction getDownstream();

    // The builder for the props interface
    public static class Builder {
        private IFunction downstream;

        public Builder downstream(final IFunction function) {
            this.downstream = function;
            return this;
        }

        public HitCounterProps build() {
            if (this.downstream == null) {
                throw new NullPointerException("The downstream property is required!");
            }

            return new HitCounterProps() {
                @Override
                public IFunction getDownstream() {
                    return downstream;
                }
            };
        }
    }
}
