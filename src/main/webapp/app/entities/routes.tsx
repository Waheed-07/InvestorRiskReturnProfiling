import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PersonalDetails from './personal-details';
import Country from './country';
import FinancialDetails from './financial-details';
import Profession from './profession';
import Address from './address';
import Currency from './currency';
import Contact from './contact';
import Questions from './questions';
import UserResponses from './user-responses';
import Options from './options';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="personal-details/*" element={<PersonalDetails />} />
        <Route path="country/*" element={<Country />} />
        <Route path="financial-details/*" element={<FinancialDetails />} />
        <Route path="profession/*" element={<Profession />} />
        <Route path="address/*" element={<Address />} />
        <Route path="currency/*" element={<Currency />} />
        <Route path="contact/*" element={<Contact />} />
        <Route path="questions/*" element={<Questions />} />
        <Route path="user-responses/*" element={<UserResponses />} />
        <Route path="options/*" element={<Options />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
