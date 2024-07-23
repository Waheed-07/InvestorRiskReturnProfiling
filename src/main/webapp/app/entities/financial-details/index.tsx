import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FinancialDetails from './financial-details';
import FinancialDetailsDetail from './financial-details-detail';
import FinancialDetailsUpdate from './financial-details-update';
import FinancialDetailsDeleteDialog from './financial-details-delete-dialog';

const FinancialDetailsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FinancialDetails />} />
    <Route path="new" element={<FinancialDetailsUpdate />} />
    <Route path=":id">
      <Route index element={<FinancialDetailsDetail />} />
      <Route path="edit" element={<FinancialDetailsUpdate />} />
      <Route path="delete" element={<FinancialDetailsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FinancialDetailsRoutes;
