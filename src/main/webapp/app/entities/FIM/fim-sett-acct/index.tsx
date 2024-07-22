import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimSettAcct from './fim-sett-acct';
import FimSettAcctDetail from './fim-sett-acct-detail';
import FimSettAcctUpdate from './fim-sett-acct-update';
import FimSettAcctDeleteDialog from './fim-sett-acct-delete-dialog';

const FimSettAcctRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimSettAcct />} />
    <Route path="new" element={<FimSettAcctUpdate />} />
    <Route path=":id">
      <Route index element={<FimSettAcctDetail />} />
      <Route path="edit" element={<FimSettAcctUpdate />} />
      <Route path="delete" element={<FimSettAcctDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimSettAcctRoutes;
