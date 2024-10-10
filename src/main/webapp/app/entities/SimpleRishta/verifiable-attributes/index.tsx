import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VerifiableAttributes from './verifiable-attributes';
import VerifiableAttributesDetail from './verifiable-attributes-detail';
import VerifiableAttributesUpdate from './verifiable-attributes-update';
import VerifiableAttributesDeleteDialog from './verifiable-attributes-delete-dialog';

const VerifiableAttributesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<VerifiableAttributes />} />
    <Route path="new" element={<VerifiableAttributesUpdate />} />
    <Route path=":id">
      <Route index element={<VerifiableAttributesDetail />} />
      <Route path="edit" element={<VerifiableAttributesUpdate />} />
      <Route path="delete" element={<VerifiableAttributesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VerifiableAttributesRoutes;
