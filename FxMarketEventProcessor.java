package com.cts.hc.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.hc.model.Trade;
import com.cts.hc.readers.FxMarketEvent;



/**
 * The Class FxMarketEventProcessor.
 * 
 * @author 
 */
public class FxMarketEventProcessor implements ItemProcessor<FxMarketEvent, Trade> {

	private static final Logger logger = LoggerFactory.getLogger(FxMarketEventProcessor.class);
   @Autowired
	private TradeValidator tradeValidator;
	@Override
	public Trade process(final FxMarketEvent fxMarketEvent) throws Exception {
        
		final String stock = fxMarketEvent.getStock();
		final String time = fxMarketEvent.getTime();
		final double price = Double.valueOf(fxMarketEvent.getPrice());
		final long shares = Long.valueOf(fxMarketEvent.getShares());
		final Trade trade = new Trade(stock, time, price, shares);
		tradeValidator.validate(trade);
		logger.trace("Converting (" + fxMarketEvent + ") into (" + trade + ")");

		return trade;
	}

}
