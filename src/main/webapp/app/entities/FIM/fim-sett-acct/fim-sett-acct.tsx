import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './fim-sett-acct.reducer';

export const FimSettAcct = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const fimSettAcctList = useAppSelector(state => state.fim.fimSettAcct.entities);
  const loading = useAppSelector(state => state.fim.fimSettAcct.loading);
  const totalItems = useAppSelector(state => state.fim.fimSettAcct.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="fim-sett-acct-heading" data-cy="FimSettAcctHeading">
        <Translate contentKey="fimApp.fimFimSettAcct.home.title">Fim Sett Accts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimSettAcct.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/fim/fim-sett-acct/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimSettAcct.home.createLabel">Create new Fim Sett Acct</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimSettAcctList && fimSettAcctList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('settaccId')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.settaccId">Settacc Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settaccId')} />
                </th>
                <th className="hand" onClick={sort('accountId')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.accountId">Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountId')} />
                </th>
                <th className="hand" onClick={sort('settAcctNbr')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.settAcctNbr">Sett Acct Nbr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctNbr')} />
                </th>
                <th className="hand" onClick={sort('settCcy')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.settCcy">Sett Ccy</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settCcy')} />
                </th>
                <th className="hand" onClick={sort('settAcctStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.settAcctStatus">Sett Acct Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctStatus')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcct.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimSettAcctList.map((fimSettAcct, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-sett-acct/${fimSettAcct.id}`} color="link" size="sm">
                      {fimSettAcct.id}
                    </Button>
                  </td>
                  <td>{fimSettAcct.settaccId}</td>
                  <td>{fimSettAcct.accountId}</td>
                  <td>{fimSettAcct.settAcctNbr}</td>
                  <td>{fimSettAcct.settCcy}</td>
                  <td>{fimSettAcct.settAcctStatus}</td>
                  <td>{fimSettAcct.createdBy}</td>
                  <td>
                    {fimSettAcct.createdTs ? <TextFormat type="date" value={fimSettAcct.createdTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimSettAcct.updatedBy}</td>
                  <td>
                    {fimSettAcct.updatedTs ? <TextFormat type="date" value={fimSettAcct.updatedTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimSettAcct.recordStatus}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/fim/fim-sett-acct/${fimSettAcct.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/fim/fim-sett-acct/${fimSettAcct.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        onClick={() =>
                          (window.location.href = `/fim/fim-sett-acct/${fimSettAcct.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
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
              <Translate contentKey="fimApp.fimFimSettAcct.home.notFound">No Fim Sett Accts found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={fimSettAcctList && fimSettAcctList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default FimSettAcct;
