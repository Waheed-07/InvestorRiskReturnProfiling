import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import { ReducersMapObject, combineReducers } from '@reduxjs/toolkit';

import getStore from 'app/config/store';

import entitiesReducers from './reducers';

import FimAccounts from './FIM/fim-accounts';
import FimAccountsWq from './FIM/fim-accounts-wq';
import FimAccountsHistory from './FIM/fim-accounts-history';
import FimSettAcct from './FIM/fim-sett-acct';
import FimSettAcctWq from './FIM/fim-sett-acct-wq';
import FimSettAcctHistory from './FIM/fim-sett-acct-history';
import FimCust from './FIM/fim-cust';
import FimCustWq from './FIM/fim-cust-wq';
import FimCustHistory from './FIM/fim-cust-history';
import Ethnicity from './FIM/ethnicity';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  const store = getStore();
  store.injectReducer('fim', combineReducers(entitiesReducers as ReducersMapObject));
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="/fim-accounts/*" element={<FimAccounts />} />
        <Route path="/fim-accounts-wq/*" element={<FimAccountsWq />} />
        <Route path="/fim-accounts-history/*" element={<FimAccountsHistory />} />
        <Route path="/fim-sett-acct/*" element={<FimSettAcct />} />
        <Route path="/fim-sett-acct-wq/*" element={<FimSettAcctWq />} />
        <Route path="/fim-sett-acct-history/*" element={<FimSettAcctHistory />} />
        <Route path="/fim-cust/*" element={<FimCust />} />
        <Route path="/fim-cust-wq/*" element={<FimCustWq />} />
        <Route path="/fim-cust-history/*" element={<FimCustHistory />} />
        <Route path="/ethnicity/*" element={<Ethnicity />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
