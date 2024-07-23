import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './financial-details.reducer';

export const FinancialDetails = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const financialDetailsList = useAppSelector(state => state.financialDetails.entities);
  const loading = useAppSelector(state => state.financialDetails.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="financial-details-heading" data-cy="FinancialDetailsHeading">
        <Translate contentKey="myApp.financialDetails.home.title">Financial Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="myApp.financialDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/financial-details/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="myApp.financialDetails.home.createLabel">Create new Financial Details</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {financialDetailsList && financialDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="myApp.financialDetails.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('annualIncome')}>
                  <Translate contentKey="myApp.financialDetails.annualIncome">Annual Income</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('annualIncome')} />
                </th>
                <th className="hand" onClick={sort('netWorth')}>
                  <Translate contentKey="myApp.financialDetails.netWorth">Net Worth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('netWorth')} />
                </th>
                <th className="hand" onClick={sort('currentSavings')}>
                  <Translate contentKey="myApp.financialDetails.currentSavings">Current Savings</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currentSavings')} />
                </th>
                <th className="hand" onClick={sort('investmentExperience')}>
                  <Translate contentKey="myApp.financialDetails.investmentExperience">Investment Experience</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('investmentExperience')} />
                </th>
                <th className="hand" onClick={sort('sourceOfFunds')}>
                  <Translate contentKey="myApp.financialDetails.sourceOfFunds">Source Of Funds</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sourceOfFunds')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="myApp.financialDetails.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="myApp.financialDetails.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {financialDetailsList.map((financialDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/financial-details/${financialDetails.id}`} color="link" size="sm">
                      {financialDetails.id}
                    </Button>
                  </td>
                  <td>{financialDetails.annualIncome}</td>
                  <td>{financialDetails.netWorth}</td>
                  <td>{financialDetails.currentSavings}</td>
                  <td>{financialDetails.investmentExperience}</td>
                  <td>{financialDetails.sourceOfFunds}</td>
                  <td>
                    {financialDetails.createdAt ? (
                      <TextFormat type="date" value={financialDetails.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {financialDetails.updatedAt ? (
                      <TextFormat type="date" value={financialDetails.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/financial-details/${financialDetails.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/financial-details/${financialDetails.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/financial-details/${financialDetails.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="myApp.financialDetails.home.notFound">No Financial Details found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FinancialDetails;
