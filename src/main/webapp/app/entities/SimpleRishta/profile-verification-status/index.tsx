import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ProfileVerificationStatus from './profile-verification-status';
import ProfileVerificationStatusDetail from './profile-verification-status-detail';
import ProfileVerificationStatusUpdate from './profile-verification-status-update';
import ProfileVerificationStatusDeleteDialog from './profile-verification-status-delete-dialog';

const ProfileVerificationStatusRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ProfileVerificationStatus />} />
    <Route path="new" element={<ProfileVerificationStatusUpdate />} />
    <Route path=":id">
      <Route index element={<ProfileVerificationStatusDetail />} />
      <Route path="edit" element={<ProfileVerificationStatusUpdate />} />
      <Route path="delete" element={<ProfileVerificationStatusDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ProfileVerificationStatusRoutes;
